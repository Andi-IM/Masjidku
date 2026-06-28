import os
import re

dao_dir = r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\model\kegiatan"
for root, dirs, files in os.walk(dao_dir):
    for f in files:
        if f.endswith("Dao.java"):
            file_path = os.path.join(root, f)
            with open(file_path, 'r', encoding='utf-8') as file:
                content = file.read()
            
            print(f"--- {f} ---")
            methods = re.findall(r'public\s+(?!class\s+)([A-Za-z0-9_<>\[\]]+)\s+([A-Za-z0-9_]+)\s*\(', content)
            for m in methods:
                if m[1] != f.replace('.java', '') and m[1] != "getConnection": # Exclude constructor and getConnection
                    print(f"{m[0]} {m[1]}")
