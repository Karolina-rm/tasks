call runcrud
if "%ERRORLEVEL%" == "0" goto :open_browser
echo.
echo RUNCRUD has errors - breaking work
goto fail

:open_browser
start chrome http://localhost:8081/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open browser - breaking work
goto fail

:fail
echo.
echo There were errors.

:end
echo.
echo Work is finished.