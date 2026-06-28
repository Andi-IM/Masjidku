import os
import re

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        lines = f.readlines()
        
    services = set()
    pattern = re.compile(r'^\s*(?:private\s+|public\s+|protected\s+|final\s+)*([A-Za-z0-9_]+)\s+([A-Za-z0-9_]+)\s*=\s*ServiceProvider\.get\(\s*\1\.class\s*\);\s*$')
    
    for line in lines:
        match = pattern.search(line)
        if match:
            t, n = match.groups()
            for exist_t, exist_n in services:
                if exist_n == n and exist_t != t:
                    print(f"CONFLICT in {filepath}: {n} is used for {exist_t} and {t}")
            services.add((t, n))

for root, dirs, files in os.walk('masjidku-app'):
    for f in files:
        if f.endswith('.java'):
            process_file(os.path.join(root, f))
