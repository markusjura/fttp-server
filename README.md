# Log Server

Produces dummy log data every 2 seconds.

You can start multiple instances of the server by

```
activator "run 8000" -Dlog.host.url="http://localhost:8000"
activator "run 8001" -Dlog.host.url="http://localhost:8001"
activator "run 8002" -Dlog.host.url="http://localhost:8002"
```
