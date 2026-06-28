import os
import shutil
import re

# 1. Create masjidku-common directory structure
common_src_dir = r"masjidku-common\src\main\java\org\masjidku\util\db"
os.makedirs(common_src_dir, exist_ok=True)

# 2. Move DatabaseConnection.java
app_db_conn = r"masjidku-app\src\main\java\org\masjidku\util\db\DatabaseConnection.java"
common_db_conn = os.path.join(common_src_dir, "DatabaseConnection.java")
shutil.move(app_db_conn, common_db_conn)

# 3. Create build.gradle.kts for masjidku-common
with open(r"masjidku-common\build.gradle.kts", "w", encoding="utf-8") as f:
    f.write("""plugins {
    java
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.42.0.0")
    implementation("mysql:mysql-connector-java:8.0.30")
}
""")

# 4. Create module-info.java for masjidku-common
with open(r"masjidku-common\src\main\java\module-info.java", "w", encoding="utf-8") as f:
    f.write("""module org.masjidku.common {
    requires java.sql;
    requires mysql.connector.java;
    exports org.masjidku.util.db;
}
""")

# 5. Add to settings.gradle
with open("settings.gradle", "r", encoding="utf-8") as f:
    settings = f.read()
if "'masjidku-common'" not in settings:
    settings += "\ninclude 'masjidku-common'\n"
    with open("settings.gradle", "w", encoding="utf-8") as f:
        f.write(settings)

# 6. Delete other DatabaseConnections
other_db_conns = [
    r"masjidku-accounting\src\main\java\org\masjidku\accounting\dao\base\DatabaseConnection.java",
    r"masjidku-events\src\main\java\org\masjidku\events\dao\base\DatabaseConnection.java",
    r"masjidku-reporting\src\main\java\org\masjidku\reporting\util\DatabaseConnection.java"
]
for p in other_db_conns:
    if os.path.exists(p):
        os.remove(p)

# 7. Update build.gradle.kts and module-info.java in other modules
def add_dependency(build_file):
    if not os.path.exists(build_file): return
    with open(build_file, "r", encoding="utf-8") as f:
        content = f.read()
    if 'implementation(project(":masjidku-common"))' not in content:
        content = content.replace("dependencies {", 'dependencies {\n    implementation(project(":masjidku-common"))')
        with open(build_file, "w", encoding="utf-8") as f:
            f.write(content)

def add_module_requires(module_file):
    if not os.path.exists(module_file): return
    with open(module_file, "r", encoding="utf-8") as f:
        content = f.read()
    if 'requires org.masjidku.common;' not in content:
        # insert after requires java.sql; or similar
        content = re.sub(r'(requires java\.sql;)', r'\1\n    requires org.masjidku.common;', content)
        # if java.sql wasn't found, just insert at top
        if 'requires org.masjidku.common;' not in content:
            content = re.sub(r'module\s+[a-zA-Z0-9_.]+\s*{', r'\g<0>\n    requires org.masjidku.common;', content)
        # remove exports org.masjidku.util.db; from masjidku-app since it's now in common
        content = content.replace('exports org.masjidku.util.db;', '')
        with open(module_file, "w", encoding="utf-8") as f:
            f.write(content)

modules = ["masjidku-app", "masjidku-accounting", "masjidku-events", "masjidku-reporting"]
for m in modules:
    add_dependency(f"{m}/build.gradle.kts")
    add_module_requires(f"{m}/src/main/java/module-info.java")

# 8. Update imports in DAOs
def replace_import(file_path, old_import, new_import="import org.masjidku.util.db.DatabaseConnection;"):
    if not os.path.exists(file_path): return
    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()
    content = content.replace(old_import, new_import)
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(content)

replace_import(r"masjidku-accounting\src\main\java\org\masjidku\accounting\dao\base\DaoFactory.java", "import org.masjidku.accounting.dao.base.DatabaseConnection;")
replace_import(r"masjidku-events\src\main\java\org\masjidku\events\dao\base\Dao.java", "import org.masjidku.events.dao.base.DatabaseConnection;")

# Check for reporting usages
reporting_dir = r"masjidku-reporting\src\main\java\org\masjidku\reporting"
if os.path.exists(reporting_dir):
    for root, _, files in os.walk(reporting_dir):
        for file in files:
            if file.endswith(".java"):
                replace_import(os.path.join(root, file), "import org.masjidku.reporting.util.DatabaseConnection;")

print("Centralization complete.")
