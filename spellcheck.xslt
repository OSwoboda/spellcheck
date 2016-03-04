<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:letex="http://www.le-tex.de/namespace"
    exclude-result-prefixes="xs"
    version="2.0">
    
    <xsl:template match="text">
		<xsl:variable name="result" select="letex:spellcheck(., 'de_DE')"/>
		<xsl:message select="$result[2] instance of xs:string, $result[1] instance of xs:boolean, count($result), $result[1]"/>
		<xsl:value-of select="$result"/>
    </xsl:template>
    
</xsl:stylesheet>