package com.rts_itsolutions.pds.model.util;

import java.util.UUID;

import org.openapplications.oagis._9.CodeType;
import org.openapplications.oagis._9.IdentifierType;
import org.openapplications.oagis._9.NameType;
import org.openapplications.oagis._9.TextType;
import org.starstandard.star._5.DocumentIdentificationGroupType;
import org.starstandard.star._5.DocumentIdentificationType;

public class PdsObjectModelHelper {

	public static NameType createNameType(String value) {
		NameType nt = new NameType();
		nt.setValue(value);
		return nt;
	}
	
	public static TextType createTextType(String value) {
		TextType tt = new TextType();
		tt.setValue(value);
		return tt;
	}
	
	public static IdentifierType createIdentifierType(String value) {
		IdentifierType it = new IdentifierType();
		it.setValue(value);
		return it;
	}

	public static CodeType createCodeType(String value) {
		CodeType codeType = new CodeType();
		codeType.setValue(value);
		return codeType;
	}
	
	/**
	 * Creates a DocumentIdentificationGroupType with a documentId that's randomly generated.
	 * @param documentId
	 * @return
	 */
	public static DocumentIdentificationGroupType createDocumentIdentificationGroup() {
		return createDocumentIdentificationGroup(UUID.randomUUID().toString());
	}
	
	public static DocumentIdentificationGroupType createDocumentIdentificationGroup(String documentId) {
		DocumentIdentificationGroupType docGroupType = new DocumentIdentificationGroupType();
		DocumentIdentificationType docIdentificationType = new DocumentIdentificationType();
		
		docGroupType.setDocumentIdentification(docIdentificationType);
		IdentifierType docId = createIdentifierType(documentId);
		docIdentificationType.setDocumentID(docId);
		return docGroupType;	
	}
	
}
