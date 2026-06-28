import os
import re

dao_dir = r"D:\01_Projects\Masjidku\masjidku-events\src\main\java\org\masjidku\events\dao\impl"
for root, dirs, files in os.walk(dao_dir):
    for f in files:
        if f.endswith("Dao.java"):
            file_path = os.path.join(root, f)
            with open(file_path, 'r', encoding='utf-8') as file:
                content = file.read()
            
            # Extract class name
            class_name = f.replace('.java', '')
            
            # Check if there is already a constructor
            if f'public {class_name}()' not in content:
                # Add constructor after class declaration
                class_decl = re.search(r'public class ' + class_name + r'.*?\{', content)
                if class_decl:
                    constructor = f"\n    public {class_name}() {{\n        getConnection();\n    }}\n"
                    content = content[:class_decl.end()] + constructor + content[class_decl.end():]
                    
                    with open(file_path, 'w', encoding='utf-8') as fw:
                        fw.write(content)
