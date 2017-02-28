Sample Spring MVC microservice app
==================================

Sample Spring MVC + Tomcat microservice application 
demonstrating no redeployment no rebuild development cycle by using
just standard Java features (no specific IDE support required).

Run the app directly from IDE by executing 
`Start.java`. Open the app at `http://localhost:8083`. 
Make a change in `index.jsp` and the app should reflect 
the change instantly.

Run it directly from IDE by executing `Start.java` **in Debug mode**. 
Open the app at `http://localhost:8083`. Make a change in `SampleModel.java`.
and the app should reflect the change instantly.

Note: If using Eclipse each change in Java source will recompile automatically. In IntelliJ
you need to trigger recompilation manually by Build -> Recompile or Build -> Build Project (⌘B). 
