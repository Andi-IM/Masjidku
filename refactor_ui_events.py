import os
import re

files_to_edit = [
    r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\MainApp.java",
    r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\principal\report\kegiatan\KegiatanOverview.java",
    r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\principal\report\kegiatan\ListKegiatan.java",
    r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\principal\report\kegiatan\ListTamu.java",
    r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\principal\report\kegiatan\ListUndangan.java",
    r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\secretary\SecretaryKegiatan.java",
    r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\secretary\SecretaryKegiatanForm.java",
    r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\secretary\SecretaryTamu.java",
    r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\secretary\SecretaryTamuForm.java",
    r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\secretary\SecretaryUndangan.java",
    r"D:\01_Projects\Masjidku\src\main\java\org\masjidku\secretary\SecretaryUndanganForm.java"
]

for file_path in files_to_edit:
    if not os.path.exists(file_path):
        continue
    
    with open(file_path, 'r', encoding='utf-8') as file:
        content = file.read()
    
    # 1. Imports
    content = content.replace("import org.masjidku.model.kegiatan.Kegiatan;", "import org.masjidku.events.client.model.Kegiatan;")
    content = content.replace("import org.masjidku.model.kegiatan.Tamu;", "import org.masjidku.events.client.model.Tamu;")
    content = content.replace("import org.masjidku.model.kegiatan.TamuKegiatan;", "import org.masjidku.events.client.model.TamuKegiatan;")
    
    content = content.replace("import org.masjidku.model.kegiatan.KegiatanDao;", "import org.masjidku.events.client.service.KegiatanService;")
    content = content.replace("import org.masjidku.model.kegiatan.TamuDao;", "import org.masjidku.events.client.service.TamuService;")
    content = content.replace("import org.masjidku.model.kegiatan.TamuKegiatanDao;", "import org.masjidku.events.client.service.TamuKegiatanService;")
    
    # Remove unused DaoFactory import
    content = re.sub(r'import org\.masjidku\.model\.kegiatan\.\w+Factory;\n', '', content)
    
    # 2. Type declarations
    content = content.replace("KegiatanDao", "KegiatanService")
    content = content.replace("TamuDao", "TamuService")
    content = content.replace("TamuKegiatanDao", "TamuKegiatanService")
    
    # 3. Instantiations
    content = content.replace("new KegiatanService()", "java.util.ServiceLoader.load(KegiatanService.class).findFirst().orElseThrow()")
    content = content.replace("new TamuService()", "java.util.ServiceLoader.load(TamuService.class).findFirst().orElseThrow()")
    content = content.replace("new TamuKegiatanService()", "java.util.ServiceLoader.load(TamuKegiatanService.class).findFirst().orElseThrow()")
    
    content = content.replace("TamuKegiatanServiceFactory.getTamuKegiatanDao()", "java.util.ServiceLoader.load(TamuKegiatanService.class).findFirst().orElseThrow()")
    
    # 4. Remove getConnection
    content = re.sub(r'if\s*\(\s*dao\.getConnection\(\)\s*\)\s*\{', '', content)
    content = re.sub(r'if\s*\(\s*dao1\.getConnection\(\)\s*\)\s*\{', '', content)
    
    # Note: If we remove the if statement, the block inside might have a matching closing brace.
    # It's safer to just replace if (dao.getConnection()) with if (true) to avoid breaking braces!
    
    with open(file_path, 'r', encoding='utf-8') as file:
        content = file.read() # Re-read to do the if(true) safely
        
    content = content.replace("import org.masjidku.model.kegiatan.Kegiatan;", "import org.masjidku.events.client.model.Kegiatan;")
    content = content.replace("import org.masjidku.model.kegiatan.Tamu;", "import org.masjidku.events.client.model.Tamu;")
    content = content.replace("import org.masjidku.model.kegiatan.TamuKegiatan;", "import org.masjidku.events.client.model.TamuKegiatan;")
    
    content = content.replace("import org.masjidku.model.kegiatan.KegiatanDao;", "import org.masjidku.events.client.service.KegiatanService;")
    content = content.replace("import org.masjidku.model.kegiatan.TamuDao;", "import org.masjidku.events.client.service.TamuService;")
    content = content.replace("import org.masjidku.model.kegiatan.TamuKegiatanDao;", "import org.masjidku.events.client.service.TamuKegiatanService;")
    
    content = re.sub(r'import org\.masjidku\.model\.kegiatan\.\w+Factory;\n', '', content)
    
    content = content.replace("KegiatanDao", "KegiatanService")
    content = content.replace("TamuDao", "TamuService")
    content = content.replace("TamuKegiatanDao", "TamuKegiatanService")
    
    content = content.replace("new KegiatanService()", "java.util.ServiceLoader.load(KegiatanService.class).findFirst().orElseThrow()")
    content = content.replace("new TamuService()", "java.util.ServiceLoader.load(TamuService.class).findFirst().orElseThrow()")
    content = content.replace("new TamuKegiatanService()", "java.util.ServiceLoader.load(TamuKegiatanService.class).findFirst().orElseThrow()")
    
    content = content.replace("TamuKegiatanServiceFactory.getTamuKegiatanDao()", "java.util.ServiceLoader.load(TamuKegiatanService.class).findFirst().orElseThrow()")
    content = content.replace("TamuKegiatanServiceFactory.getTamuKegiatanService()", "java.util.ServiceLoader.load(TamuKegiatanService.class).findFirst().orElseThrow()")

    content = re.sub(r'dao\.getConnection\(\)', 'true', content)
    content = re.sub(r'dao1\.getConnection\(\)', 'true', content)
    content = re.sub(r'kegiatanDao\.getConnection\(\)', 'true', content)
    content = re.sub(r'tamuDao\.getConnection\(\)', 'true', content)
    
    with open(file_path, 'w', encoding='utf-8') as file:
        file.write(content)

print("Done refactoring UI controllers!")
