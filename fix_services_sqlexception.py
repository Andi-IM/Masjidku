import os
import re

service_dir = r"D:\01_Projects\Masjidku\masjidku-events-client\src\main\java\org\masjidku\events\client\service"

for f in os.listdir(service_dir):
    if f.endswith("Service.java"):
        file_path = os.path.join(service_dir, f)
        with open(file_path, 'r', encoding='utf-8') as file:
            content = file.read()
        
        # Replace method signatures to add throws SQLException
        content = re.sub(r'([^;{}]+)\s*;', r'\1 throws java.sql.SQLException;', content)
        
        # Fix double throws
        content = content.replace('throws java.sql.SQLException throws java.sql.SQLException', 'throws java.sql.SQLException')
        content = content.replace('package org.masjidku.events.client.service throws java.sql.SQLException;', 'package org.masjidku.events.client.service;')
        content = content.replace('import javafx.collections.ObservableList throws java.sql.SQLException;', 'import javafx.collections.ObservableList;')
        content = re.sub(r'import\s+org\.masjidku\.events\.client\.model\.[A-Za-z0-9_]+\s+throws java\.sql\.SQLException;', lambda m: m.group(0).replace(' throws java.sql.SQLException', ''), content)
        
        with open(file_path, 'w', encoding='utf-8') as fw:
            fw.write(content)

# Also remove @Override from TamuKegiatanDao
dao_path = r"D:\01_Projects\Masjidku\masjidku-events\src\main\java\org\masjidku\events\dao\impl\TamuKegiatanDao.java"
with open(dao_path, 'r', encoding='utf-8') as f:
    dao_content = f.read()

dao_content = re.sub(r'@Override\s+', '', dao_content)

with open(dao_path, 'w', encoding='utf-8') as fw:
    fw.write(dao_content)

