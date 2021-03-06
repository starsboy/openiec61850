/**
 * This class file was automatically generated by jASN1 v1.8.0 (http://www.openmuc.org)
 */

package org.openmuc.openiec61850.internal.mms.asn1;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.openmuc.jasn1.ber.BerByteArrayOutputStream;
import org.openmuc.jasn1.ber.BerLength;
import org.openmuc.jasn1.ber.BerTag;

public class VariableAccessSpecification implements Serializable {

    private static final long serialVersionUID = 1L;

    public byte[] code = null;
    private VariableDefs listOfVariable = null;
    private ObjectName variableListName = null;

    public VariableAccessSpecification() {
    }

    public VariableAccessSpecification(byte[] code) {
        this.code = code;
    }

    public void setListOfVariable(VariableDefs listOfVariable) {
        this.listOfVariable = listOfVariable;
    }

    public VariableDefs getListOfVariable() {
        return listOfVariable;
    }

    public void setVariableListName(ObjectName variableListName) {
        this.variableListName = variableListName;
    }

    public ObjectName getVariableListName() {
        return variableListName;
    }

    public int encode(BerByteArrayOutputStream os) throws IOException {

        if (code != null) {
            for (int i = code.length - 1; i >= 0; i--) {
                os.write(code[i]);
            }
            return code.length;
        }

        int codeLength = 0;
        int sublength;

        if (variableListName != null) {
            sublength = variableListName.encode(os);
            codeLength += sublength;
            codeLength += BerLength.encodeLength(os, sublength);
            // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
            os.write(0xA1);
            codeLength += 1;
            return codeLength;
        }

        if (listOfVariable != null) {
            codeLength += listOfVariable.encode(os, false);
            // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
            os.write(0xA0);
            codeLength += 1;
            return codeLength;
        }

        throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
    }

    public int decode(InputStream is) throws IOException {
        return decode(is, null);
    }

    public int decode(InputStream is, BerTag berTag) throws IOException {

        int codeLength = 0;
        BerTag passedTag = berTag;

        if (berTag == null) {
            berTag = new BerTag();
            codeLength += berTag.decode(is);
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
            listOfVariable = new VariableDefs();
            codeLength += listOfVariable.decode(is, false);
            return codeLength;
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
            codeLength += BerLength.skip(is);
            variableListName = new ObjectName();
            codeLength += variableListName.decode(is, null);
            return codeLength;
        }

        if (passedTag != null) {
            return 0;
        }

        throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
        BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
        encode(os);
        code = os.getArray();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendAsString(sb, 0);
        return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

        if (listOfVariable != null) {
            sb.append("listOfVariable: ");
            listOfVariable.appendAsString(sb, indentLevel + 1);
            return;
        }

        if (variableListName != null) {
            sb.append("variableListName: ");
            variableListName.appendAsString(sb, indentLevel + 1);
            return;
        }

        sb.append("<none>");
    }

}
