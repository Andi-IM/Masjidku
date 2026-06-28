import os
import re

def refactor(path):
    with open(path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # 1. Extract and remove TABLE constant
    tm = re.search(r'(?:private\s+final\s+)?String\s+TABLE\s*=\s*"([^"]+)";', content)
    if not tm:
        return
    table = tm.group(1)
    content = content.replace(tm.group(0), "")
    
    # 2. Replace TABLE concatenation with inline table name
    content = content.replace('"+TABLE+"', table)
    content = content.replace('" + TABLE + "', table)
    content = content.replace('"+TABLE', table + '"')
    content = content.replace('TABLE+"', '"' + table)
    content = content.replace('TABLE + "', '"' + table)
    content = content.replace('" + TABLE', '"' + table)

    # 3. Handle query = "..." by converting it to a constant at the top
    # We will find all assignments to query (or String query)
    # This regex matches: query = "..." or query = "..." + "..."
    # It stops at the semicolon.
    queries = []
    
    def query_repl(m):
        q_val = m.group(1).strip()
        # Create a constant for this
        const_name = f"QUERY_{len(queries)+1}"
        queries.append(f'    private static final String {const_name} = {q_val};')
        return f'ps = con.prepareStatement({const_name});'

    # match `query = ...;` or `String query = ...;`
    # the value can span multiple lines
    content = re.sub(r'(?:String\s+)?query\s*=\s*([^;]+);', query_repl, content)
    
    # Now remove old ps = con.prepareStatement(query);
    content = re.sub(r'ps\s*=\s*con\.prepareStatement\(query\)\s*;\s*', '', content)
    
    # Insert constants after the class declaration
    if queries:
        class_idx = content.find('public class')
        if class_idx != -1:
            brace_idx = content.find('{', class_idx)
            if brace_idx != -1:
                insert_str = "\n" + "\n".join(queries) + "\n"
                content = content[:brace_idx+1] + insert_str + content[brace_idx+1:]
                
    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)
    print(f"Refactored {path}")

dirs = [
    r"D:\01_Projects\Masjidku\masjidku-accounting\src\main\java\org\masjidku\accounting\dao",
    r"D:\01_Projects\Masjidku\masjidku-events\src\main\java\org\masjidku\events\dao",
    r"D:\01_Projects\Masjidku\masjidku-reporting\src\main\java\org\masjidku\reporting\dao"
]

for d in dirs:
    if os.path.exists(d):
        for root, ds, files in os.walk(d):
            for f in files:
                if f.endswith('.java'):
                    refactor(os.path.join(root, f))

print("Refactoring complete.")
