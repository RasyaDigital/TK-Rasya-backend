import pymysql
import re

# Connection details
host = 'interchange.proxy.rlwy.net'
port = 58908
user = 'root'
password = 'uISjJluiFwoDoZmuSAUdZEJvkhBNKFhC'
database = 'railway'

def execute_sql_file(filename, connection):
    with open(filename, 'r', encoding='utf-8') as f:
        sql = f.read()

    # Remove comments
    sql = re.sub(r'--.*?\n', '\n', sql)
    sql = re.sub(r'/\*.*?\*/', '', sql, flags=re.DOTALL)

    # Split into statements
    statements = sql.split(';')
    
    with connection.cursor() as cursor:
        for statement in statements:
            stmt = statement.strip()
            if stmt:
                try:
                    cursor.execute(stmt)
                except Exception as e:
                    print(f"Error executing statement: {stmt[:50]}...")
                    print(f"Error: {e}")
                    raise e
    connection.commit()

try:
    print(f"Connecting to {host}:{port}...")
    conn = pymysql.connect(
        host=host,
        port=port,
        user=user,
        password=password,
        database=database,
        charset='utf8mb4',
        cursorclass=pymysql.cursors.DictCursor
    )
    print("Connected successfully!")
    
    print("Executing schema.sql...")
    execute_sql_file('schema.sql', conn)
    print("Import completed successfully!")

    # Verify tables
    with conn.cursor() as cursor:
        cursor.execute("SHOW TABLES")
        tables = cursor.fetchall()
        print("\nTables in database:")
        for table in tables:
            print(f"- {list(table.values())[0]}")

except Exception as e:
    print(f"Failed to connect or execute: {e}")
finally:
    if 'conn' in locals() and conn.open:
        conn.close()
