import os

def clean_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    original = content
    while True:
        idx = content.find("if (true)")
        if idx == -1:
            break
            
        # find the '{'
        start_brace = content.find("{", idx)
        if start_brace == -1:
            break
            
        # find matching '}'
        count = 1
        i = start_brace + 1
        while i < len(content) and count > 0:
            if content[i] == '{': count += 1
            elif content[i] == '}': count -= 1
            i += 1
            
        end_brace = i - 1
        
        # Check if there is an `else` block after this
        after = content[end_brace+1:]
        
        # skip whitespaces
        after_stripped = after.lstrip()
        if after_stripped.startswith("else"):
            # find the end of the else block
            else_start = end_brace + 1 + len(after) - len(after_stripped)
            else_start_brace = content.find("{", else_start)
            
            count = 1
            j = else_start_brace + 1
            while j < len(content) and count > 0:
                if content[j] == '{': count += 1
                elif content[j] == '}': count -= 1
                j += 1
            else_end_brace = j - 1
            
            # remove `if (true) {`, extract inner, remove `} else { ... }`
            
            # to keep lines clean, we might want to strip the whitespace before `if`
            # find start of line
            line_start = content.rfind('\n', 0, idx)
            if line_start == -1: line_start = 0
            
            inner = content[start_brace+1:end_brace]
            # optional: we could unindent inner by 4 spaces, but Java compiler doesn't care.
            
            content = content[:line_start] + inner + content[else_end_brace+1:]
        else:
            # no else block, just replace the if
            line_start = content.rfind('\n', 0, idx)
            if line_start == -1: line_start = 0
            
            inner = content[start_brace+1:end_brace]
            content = content[:line_start] + inner + content[end_brace+1:]

    if original != content:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)

for root, dirs, files in os.walk('masjidku-app'):
    for f in files:
        if f.endswith('.java'):
            clean_file(os.path.join(root, f))
