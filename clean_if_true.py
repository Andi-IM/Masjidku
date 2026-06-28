import os
import re

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()
        
    out_lines = []
    stack = []
    
    i = 0
    modified = False
    while i < len(lines):
        line = lines[i]
        
        match = re.search(r'^(\s*)if\s*\(true\)\s*\{\s*$', line)
        if match:
            modified = True
            indent = len(match.group(1))
            stack.append(('ifTrue', indent))
            i += 1
            continue
            
        indent = len(line) - len(line.lstrip())
        
        if stack and stack[-1][0] == 'ifTrue' and indent == stack[-1][1]:
            if re.search(r'^\s*\}\s*else\s*\{\s*$', line):
                stack[-1] = ('elseBlock', indent)
                i += 1
                continue
            elif re.search(r'^\s*\}\s*$', line):
                if i + 1 < len(lines) and re.search(r'^\s*else\s*\{\s*$', lines[i+1]):
                    stack[-1] = ('elseBlock', indent)
                    i += 2
                    continue
                else:
                    stack.pop()
                    i += 1
                    continue
                    
        if stack and stack[-1][0] == 'elseBlock' and indent == stack[-1][1]:
            if re.search(r'^\s*\}\s*$', line):
                stack.pop()
                i += 1
                continue

        if stack and stack[-1][0] == 'elseBlock':
            i += 1
            continue
            
        if stack and stack[-1][0] == 'ifTrue':
            if line.startswith('    '):
                out_lines.append(line[4:])
            else:
                out_lines.append(line)
        else:
            out_lines.append(line)
            
        i += 1

    if modified:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.writelines(out_lines)

for root, dirs, files in os.walk('masjidku-app'):
    for f in files:
        if f.endswith('.java'):
            process_file(os.path.join(root, f))
