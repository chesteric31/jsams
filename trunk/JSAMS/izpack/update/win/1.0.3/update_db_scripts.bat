@ECHO OFF
"$INSTALL_PATH\win\mysql-5.1.60-win32\bin\mysql.exe" --user=root --password=$DB_PASSWORD --database=$DB_SCHEMA < "$INSTALL_PATH\scripts\1.0.3\ALTER_TABLES_F_1_0_2_T_1_0_3.sql" --default-character-set=utf8

