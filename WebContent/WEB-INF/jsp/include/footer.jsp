<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="cp" value="${pageContext.request.contextPath}" scope="request" />
</td>
</tr>
<tr valign="top">
	<td colspan=2 align="center">						
		<br><br><p>Copyright &#169; 2015 Avnet, Inc. All rights reserved.</p>				
	</td>
</tr>
</table>
</div>


<script type="text/javascript" src="${cp}/resources/menubar/js/custom.js"></script>

<script type="text/javascript">
( function($) {
    $(document).ready(function() {
        $("#loading-div-background").css({ opacity: 0.8 });
        
		if ( typeof String.prototype.endsWith !== 'function' ) {
			String.prototype.endsWith = function(suffix) {
				return this.indexOf(suffix, this.length - suffix.length) !== -1;
			};
		}
		
		if ( typeof String.prototype.startsWith !== 'function' ) {
			String.prototype.startsWith = function(prefix) {
				return this.indexOf(prefix) == 0;
			};
		}
    });
  } ) ( jQuery );
</script>
