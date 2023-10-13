# GovTech-GDS-SWE-Challenge
<br/>

<h2>Overview</h2>
<br/>
    <ul>
      <li>User Interface built using Angular and materal UI</li>
      <li>Backend APIs built using Spring boot, JPA, H2 in-memory database and maven as build tool</li>
    </ul>

  <br/>
  <h2>Quick Links</h2>
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
      </ul>
  <br/>
   <h2>Key Features</h2>
       <ul>
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
               90% code coverage
           </li>
           <br/>
           <li>
               0 Major, blocker and critical sonar code vulnerabilities
           </li>
           <br/>
       </ul>
    <br/>
   <h2>Run</h2>
  <br/>
   <h2>Test</h2>
  <br/>
