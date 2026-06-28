import os
from collections import defaultdict

def get_java_files(root_dir):
    files = []
    for root, _, filenames in os.walk(root_dir):
        for name in filenames:
            if name.endswith('.java'):
                files.append(os.path.join(root, name))
    return files

def get_normalized_lines(filepath):
    with open(filepath, 'r', encoding='utf-8', errors='ignore') as f:
        lines = f.readlines()
    
    # Keep track of original line numbers and normalized content
    normalized = []
    for i, line in enumerate(lines):
        s = line.strip()
        # skip empty lines, imports, packages, and brackets
        if not s or s.startswith('import') or s.startswith('package') or s == '}' or s == '{' or s.startswith('//') or s.startswith('/*') or s.startswith('*'):
            continue
        # normalize spaces
        s = ' '.join(s.split())
        normalized.append((i+1, s))
    return normalized, lines

def find_duplicates(min_lines=10):
    files = get_java_files('.')
    
    # block_hash -> list of (filepath, start_line, end_line)
    blocks = defaultdict(list)
    
    for f in files:
        norm_lines, raw = get_normalized_lines(f)
        if len(norm_lines) < min_lines:
            continue
            
        for i in range(len(norm_lines) - min_lines + 1):
            chunk = norm_lines[i:i+min_lines]
            chunk_str = '\n'.join(x[1] for x in chunk)
            start_line = chunk[0][0]
            end_line = chunk[-1][0]
            blocks[chunk_str].append((f, start_line, end_line))
            
    # filter to blocks that appear in more than 1 place
    duplicates = {k: v for k, v in blocks.items() if len(v) > 1}
    
    # We want to merge overlapping chunks and present the longest duplicates
    # For a simple script, we'll just count how many times files share duplicates
    file_pairs = defaultdict(int)
    for chunk, places in duplicates.items():
        # unique files for this chunk
        unique_files = list(set([p[0] for p in places]))
        for i in range(len(unique_files)):
            for j in range(i+1, len(unique_files)):
                f1, f2 = sorted([unique_files[i], unique_files[j]])
                file_pairs[(f1, f2)] += 1
                
    print("--- DUPLICATE CODE ANALYSIS ---")
    print(f"Searched {len(files)} files for duplicated blocks of {min_lines} or more lines.")
    print("Top file pairs sharing the most duplicated lines (estimated):")
    
    sorted_pairs = sorted(file_pairs.items(), key=lambda x: x[1], reverse=True)
    for (f1, f2), count in sorted_pairs[:15]:
        b1 = os.path.basename(f1)
        b2 = os.path.basename(f2)
        print(f"- {b1} and {b2} share ~{count} overlapping duplicated blocks.")

if __name__ == "__main__":
    find_duplicates(10)
