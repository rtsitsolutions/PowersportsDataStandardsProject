# Common Object Model Project

The purpose of this project is to create a library of proxy objects in Java that allow you to build
clients for the [STAR](http://www.starstandards.org) standard.

## Before you begin

Before you begin, you should have the following installed:

- Java JDK (not JRE) 1.6+
- Apache [Maven](http://maven.apache.org/) 3.x
- An IDE or editor for editing Java files.

This project includes BOD (Business Object Documents) from the STAR site. It uses Apache Maven to 
download the necessary libraries and dependencies, then uses the plugins to generate Java class files
from the WSDLs and schemas found in the project. 

## Generating sources for the first time

After checking out the files, to generate the sources for the first time, execute the following command:

    mvn generate-sources

When the task is complete, you will see new Java files generated in $PROJECTHOME/target/generated-sources.

## Building the project

To build the project, type the following command:

    mvn compile

## Creating the JAR file

To create a JAR file with the compiled artifacts, type:

    mvn package

## Adding more services

By default, only two services are created. These services are defined in the `pom.xml` file in the 
`<wsdlOptions>` element, as shown here:

    <wsdlOption>
        <wsdl>
            ${basedir}/src/main/resources/BODs/WSDL/WS4Templates/GetVehicleSpecifications.wsdl
        </wsdl>
        <wsdlLocation>classpath:BODS/WSDL/WS4Templates/GetVehicleSpecifications.wsdl</wsdlLocation>
        <extraargs>
            <extraarg>-p</extraarg>
            <extraarg>http://www.starstandards.org/webservices/2009/transport=org.starstandards.webservices.getvehiclespecifications</extraarg>
            <extraarg>-p</extraarg>
            <extraarg>http://www.starstandards.org/webservices/2009/transport/bindings=org.starstandards.webservices.getvehiclespecifications.bindings</extraarg>
        </extraargs>
    </wsdlOption>

In this example, the `GetVehicleSpecification.wsdl` file is used to generate proxy classes. You will find the
`GetVehicleSpecificationWebService` class at 
`target/generated-sources/cxf/org/starstandards/webservices/getvehiclespecifications/bindings/GetVehicleSpecificationsWebService.java`

To use the class, type something like the following:

    import org.openapplications.oagis._9.CodeType;
    import org.openapplications.oagis._9.TextType;
    import org.openapplications.oagis._9.unqualifieddatatypes._1.IdentifierType;
    import org.starstandard.star._5.ApplicationAreaType;
    import org.starstandard.star._5.DestinationType;
    import org.starstandard.star._5.GetVehicleSpecificationsDataAreaType;
    import org.starstandard.star._5.GetVehicleSpecificationsType;
    import org.starstandard.star._5.SenderType;
    import org.starstandard.star._5.VehicleABIEType;
    import org.starstandard.star._5.VehicleSpecificationsLineType;
    import org.starstandard.star._5.VehicleSpecificationsType;
    import org.starstandards.webservices.getvehiclespecifications.GetVehicleSpecificationsContent;
    import org.starstandards.webservices.getvehiclespecifications.GetVehicleSpecificationsPayload;
    import org.starstandards.webservices.getvehiclespecifications.ObjectFactory;
    import org.starstandards.webservices.getvehiclespecifications.ProcessMessage;
    import org.starstandards.webservices.getvehiclespecifications.ProcessMessageResponse;

    class Foo {
    ...

        private static ProcessMessage createRequestForVin(String vin) {

            ObjectFactory of = new ObjectFactory();
            
            ProcessMessage processMessage = of.createProcessMessage();
            
            GetVehicleSpecificationsPayload payload = of.createGetVehicleSpecificationsPayload();
            processMessage.setPayload(payload);
            
            GetVehicleSpecificationsContent content = of.createGetVehicleSpecificationsContent();
            payload.getContent().add(content);
            
            GetVehicleSpecificationsType getVehicleSpecificationsType = new GetVehicleSpecificationsType();
            content.setGetVehicleSpecifications(getVehicleSpecificationsType);
            
            ApplicationAreaType applicationAreaType = new ApplicationAreaType();
            getVehicleSpecificationsType.setApplicationArea(applicationAreaType);
            
            SenderType senderType = new SenderType();
            TextType creatorNameCode = new TextType();
            creatorNameCode.setValue("TESTNAMECODE");
            senderType.setCreatorNameCode(creatorNameCode);
            
            CodeType senderNameCode = new CodeType();
            senderNameCode.setValue("TESTSENDERNAMECODE");
            senderType.setSenderNameCode(senderNameCode);
            
            GregorianCalendar cal = new GregorianCalendar();
            
            try {
                applicationAreaType.setCreationDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
            } catch (DatatypeConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            applicationAreaType.setDestination(new DestinationType());
            
            applicationAreaType.setSender(senderType);
            
            GetVehicleSpecificationsDataAreaType dataArea = new GetVehicleSpecificationsDataAreaType();
            getVehicleSpecificationsType.setGetVehicleSpecificationsDataArea(dataArea);
            
            VehicleSpecificationsType vehicleSpec = new VehicleSpecificationsType();
            dataArea.getVehicleSpecifications().add(vehicleSpec);
            
            VehicleSpecificationsLineType specLine = new VehicleSpecificationsLineType();
            vehicleSpec.getVehicleSpecificationsLine().add(specLine);
            
            VehicleABIEType vehicle = new VehicleABIEType();
            specLine.setVehicle(vehicle);
            
            IdentifierType idType = new IdentifierType();
            idType.setValue(vin);
            vehicle.setVehicleID(idType);
            
            return processMessage;
        }
    }
