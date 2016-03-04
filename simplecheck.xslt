<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:letex="http://www.le-tex.de/namespace"
    exclude-result-prefixes="xs"
    version="2.0">
    
    <xsl:template match="text">
        <xsl:value-of select="letex:simplecheck(current(), 'de_DE')"/>
    </xsl:template>
    
</xsl:stylesheet>