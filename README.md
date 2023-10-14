# GovTech-GDS-SWE-Challenge
<br/>

<h2>Overview</h2>
<br/>
    <ul>
      <li>
          User Interface built using Angular and materal UI
          <br/>
          No of screens : 10
      </li>
      <li>
          Backend APIs built using Spring boot, JPA, H2 in-memory database and maven as build tool
          <br/>
          No of APIs : 23
      </li>
    </ul>
  <h2>Documentation Quick Links</h2>
      <h5>Documentation : <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge/tree/main/documents" target="_blank">here</a> </h5>
      <ul>
         <li>
          UI/UX screens Document : <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge/blob/main/documents/UI-UX-Screens.pptx" target="_blank">here</a>
        </li>
         <br/>
        <li>
          API Docs : <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge/blob/main/documents/api-docs.json" target="_blank">here</a>
          <br/>
          Local host URL : <a href="http://localhost:8080/api-docs" target="_blank">here</a>
        </li>
         <br/>
         <li>
          API Specification Document : <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge/blob/main/documents/APIs%20Specification.docx" target="_blank">here</a>
         </li>
         <br/>
         <li>
           Swagger UI : <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge/blob/main/documents/Swagger%20UI.pdf" target="_blank">here</a>
           <br/>
           Local host URL : <a href="http://localhost:8080/swagger-ui/index.html#/" target="_blank">here</a>
         </li>
         <br/>
         <li>
          API Postman Collection : <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge/blob/main/documents/API%20Postman%20Collection.postman_collection" target="_blank">here</a>
        </li>
          <li>
          h2 console : <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge/blob/main/documents/h2-console.png" target="_blank">here</a>
          <br/>
           Local host URL : <a href="http://localhost:8080/h2-console" target="_blank">here</a>
           <br/>
           H2 DB Backup : <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge/blob/main/documents/govtech.mv.db" target="_blank">here</a>
        </li>
      </ul>
  <br/>
   <h2>Key Features</h2>
       <ul>
            <li>
               User notifications using server sent events <b>(SSE)</b>
           </li>
             <br/>
           <li>
               Centralized logging management using <b>Spring AOP</b> <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge/blob/main/src/main/java/com/govtech/assignment/config/LoggingAspect.java" target="_blank">here</a>
           </li>
           <br/>
           <li>
               Centralized exception handling using <b> controller advise</b> <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge/blob/main/src/main/java/com/govtech/assignment/exception/ExceptionTranslator.java" target="_blank">here</a>
           </li>
           <br/>
           <li>
               Centralized distributed tracing management using <b>slueth and zipkin</b>
           </li>
           <br/>
           <li>
               API Document management using <b>Swagger Open API specification</b>
           </li>
           <br/>
           <li>
               APIs are secured with <b>Spring security using JWT</b>
           </li>
           <br/>
            <li>
               Generic mapper strategy used for converting models to entites and entities to models using <b>Map struct</b>
           </li>
           <br/>
            <li>
               90% code coverage with 50+ Junit test cases
           </li>
           <br/>
           <li>
               0 Major, blocker and critical sonar code vulnerabilities
           </li>
           <br/>
       </ul>
   <h2>Run</h2>
       <h4>
           Front end
       </h4>
       <ul>
           <li>
               Clone the source code from <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge.git" target="_blank">here</a>
           </li>
           <li>
               Navigate to the UI source folder path : src/main/frontend/govtech-assignment-ui
           </li>
           <li>
               run "npm install" to install dependencies
           </li>
            <li>
               run "npm start" or "ng server" to run the frontend application
           </li>
           <li>
               Access applciation in local <a href="http://localhost:4200/" target="_blank">here</a>
           </li>
       </ul>
       <h4>
           Backend
       </h4>
       <ul>
           <li>
               Clone the source code from <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge.git" target="_blank">here</a>
           </li>
           <li>
               Run the spring boot main application java class LocationDecisionMakerApplication.java
           </li>
       </ul>
   <h2>Test</h2>
       <h4>
           Front end
       </h4>
       <ul>
           <li>
               Access applciation in local <a href="http://localhost:4200/" target="_blank">here</a>
           </li>
           <li>
               Sign up in the application <a href="http://localhost:4200/sign-up" target="_blank">here</a>
           </li>
           <li>
               Sign in in the application <a href="http://localhost:4200/sign-in" target="_blank">here</a>
           </li>
            <li>
               Test the application by creating and inviting users into the sessions
           </li>
       </ul>
       <h4>Backend</h4>
      <ul>
         <li>
           APIs can be tested using swagger URL : <a href="http://localhost:8080/swagger-ui/index.html#/" target="_blank">here</a>
         </li>
         <li>
          APIs can be tested using postman with the API Postman Collection : <a href="https://github.com/kasireddy515/GovTech-GDS-SWE-Challenge/blob/main/documents/API%20Postman%20Collection.postman_collection" target="_blank">here</a>
        </li>
          <li>
           Database can be accessed : <a href="http://localhost:8080/h2-console" target="_blank">here</a>
           Credentials: User name: govtech, password: govtech   
        </li>
      </ul>
  <br/>
