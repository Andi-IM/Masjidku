import os
import re

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()
        
    out_lines = []
    services = set()
    class_decl_idx = -1
    
    pattern = re.compile(r'^\s*(?:private\s+|public\s+|protected\s+|final\s+)*([A-Za-z0-9_]+)\s+([A-Za-z0-9_]+)\s*=\s*ServiceProvider\.get\(\s*\1\.class\s*\);\s*$')
    class_pattern = re.compile(r'^\s*public\s+(?:abstract\s+)?class\s+[A-Za-z0-9_]+.*\{')

    for i, line in enumerate(lines):
        if class_pattern.search(line):
            class_decl_idx = i
            
        match = pattern.search(line)
        if match:
            t, n = match.groups()
            services.add((t, n))
        else:
            out_lines.append(line)
            
    if not services or class_decl_idx == -1:
        return
        
    # find where to insert (right after class declaration)
    out_class_idx = -1
    for i, line in enumerate(out_lines):
        if class_pattern.search(line):
            out_class_idx = i
            break
            
    if out_class_idx != -1:
        fields = []
        for t, n in sorted(list(services)):
            fields.append(f"    private final {t} {n} = ServiceProvider.get({t}.class);\n")
            
        out_lines = out_lines[:out_class_idx+1] + fields + out_lines[out_class_idx+1:]
        
    with open(filepath, 'w', encoding='utf-8') as f:
        f.writelines(out_lines)

for root, dirs, files in os.walk('masjidku-app'):
    for f in files:
        if f.endswith('.java'):
            process_file(os.path.join(root, f))
