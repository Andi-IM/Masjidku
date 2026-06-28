import os
import re

def process_file(filepath):
    if filepath.endswith('ServiceProvider.java'):
        return

    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
        
    pattern = r'(?:java\.util\.)?ServiceLoader\.load\(([^)]+)\)\.findFirst\(\)\.orElseThrow\(\)'
    
    if not re.search(pattern, content):
        return
        
    new_content = re.sub(pattern, r'ServiceProvider.get(\1)', content)
    
    if 'ServiceProvider.get(' in new_content:
        if 'import org.masjidku.util.ServiceProvider;' not in new_content:
            if 'import ' in new_content:
                new_content = new_content.replace('import ', 'import org.masjidku.util.ServiceProvider;\nimport ', 1)
            else:
                new_content = re.sub(r'(package\s+[^;]+;)', r'\1\n\nimport org.masjidku.util.ServiceProvider;', new_content)
                
    # If ServiceLoader is completely unused now, remove its import
    if 'ServiceLoader' not in new_content:
        new_content = re.sub(r'import\s+java\.util\.ServiceLoader;\r?\n?', '', new_content)
        
    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(new_content)

for root, dirs, files in os.walk('masjidku-app'):
    for f in files:
        if f.endswith('.java'):
            process_file(os.path.join(root, f))
