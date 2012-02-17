@ECHO OFF
echo "Execute db.sql"
"$INSTALL_PATH\mysql-5.1.60-win32\bin\mysql.exe" --user=root --password=$DB_PASSWORD --database=$DB_SCHEMA < "$INSTALL_PATH\scripts\db_win.sql"
