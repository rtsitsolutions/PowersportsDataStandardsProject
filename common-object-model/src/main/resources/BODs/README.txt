The BODS contained within here were pulled from starstandard.org using version 5.6.4

However, in order to create the Java for XML bindings, slight changes had to be made to the schema files. 
Those changes are as follows:

	1. Removed GetVehicleServiceHistoryRetrieval.xsd since it wasn't referenced by any BOD/WSDL and it was defining
	   duplicate elements already defined by GetVehicleServiceHistory.xsd.

    2. Both WarrantyClaim.xsd and RepairOrder.xsd defined a RepairOrderVehicleLineItem.  
       Removed the instance from WarrantyClaim.xsd.