<%@taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:choose>
    <c:when test="${fn:contains(pageContext.response.locale, '_')}">
        <c:set var="lang">
            ${fn:substringBefore(pageContext.response.locale, "_")}
        </c:set>
    </c:when>
    <c:otherwise>
        <c:set var="lang" value="${pageContext.response.locale}"/>
    </c:otherwise>
</c:choose>
<html lang="${lang}">
    <c:set var="title" value="Tanaguru Contrast-Finder"/>
    <%@include file='/WEB-INF/template/head.jspf' %>
    <body id="set-up-form">
        <div class="container">
            <%@include file='/WEB-INF/template/header.jspf' %>
            <div class="row">
                <div class="col-lg-12">
                    <h2><fmt:message key="form.fillInFields"/></h2>
                    <form:form class="form-horizontal" name="formulaire" commandName="colorModel" method="POST" role="form">
                        <c:set var="foregroundOnError">
                            <form:errors path="foreground"/>
                        </c:set>
                        <c:choose>
                            <c:when test="${not empty foregroundOnError}">
                                <c:set var="foregroundOnError" value="has-error"/>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                        <div class="form-group ${foregroundOnError}">
                            <label for="foreground" class="col-lg-3 control-label"><fmt:message key="form.foregroundColor"/></label>
                            <div class="col-lg-3">
                                <form:input path="foreground" type="color" class="form-control"/>
                                <span class="help-block"><fmt:message key="form.help"/></span>
                            </div>
                        </div>
                        <c:set var="backgroundOnError">
                            <form:errors path="background"/>
                        </c:set>
                        <c:choose>
                            <c:when test="${not empty backgroundOnError}">
                                <c:set var="backgroundOnError" value="has-error"/>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                        <div class="form-group ${backgroundOnError}">
                            <label for="background" class="col-lg-3 control-label"><fmt:message key="form.backgroundColor"/></label>
                            <div class="col-lg-3">
                                <form:input path="background" type="color" class="form-control"/>
                                <span class="help-block"><fmt:message key="form.help"/></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for='isBackgroundTested' class="col-lg-3 control-label"><fmt:message key="form.component"/></label>
                            <div class="col-lg-3">
                                <form:select class="form-control" path="isBackgroundTested">
                                    <form:option value="false"><fmt:message key="form.componentForeground"/></form:option>
                                    <form:option value="true"><fmt:message key="form.componentBackground"/></form:option>
                                </form:select>
                            </div>
                        </div>
                        <c:set var="ratioOnError">
                            <form:errors path="ratio"/>
                        </c:set>
                        <c:choose>
                            <c:when test="${not empty ratioOnError}">
                                <c:set var="ratioOnError" value="has-error"/>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                        <div class="form-group ${ratioOnError}">
                            <label for="ratio" class="col-lg-3 control-label"><fmt:message key="form.ratio"/></label>
                            <div class="col-lg-3">
                                <form:select class="form-control" path="ratio">
                                    <form:option value="3">3</form:option>
                                    <form:option value="4.5" >4.5</form:option>
                                    <form:option value="7">7</form:option>
                                </form:select>
                                <form:errors path="ratio" cssClass="help-block"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <fmt:message key="form.validate" var="validateButton"/>
                            <input type="submit" class="btn btn-default col-lg-offset-3 col-lg-3" name="commit" value="${validateButton}"/>
                        </div>
                    </form:form>
                </div><!-- class="col-lg-12' -->
            </div><!-- class="row' -->

            <c:if test="${not empty colorResult}">
                <c:choose> 
                    <%-- Good contrast: nothing to do --%>
                    <c:when test="${colorResult.combinaisonValid}">
                        <div class="row">
                            <div class="alert-message block-message">
                                <div class="result col-lg-12">
                                    <h2><fmt:message key="form.validContrast"/>${colorResult.submittedCombinaisonColor.contrast}</h2>
                                    <p><fmt:message key="form.validContrastDesc"/></p>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="result col-lg-12">
                                <img src="<c:url value="/Images/good.jpg"/>" alt="Correct contrast"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-offset-8">
                                <a title="Creative Commons Attribution 3.0 License" href="http://creativecommons.org/licenses/by/3.0/">
                                    <img src="<c:url value="/Images/creative_common_logo.png"/>" alt="License"> </a>
                                <a title="Flickr: Galerie de Thomas Hawk" href="http://www.flickr.com/photos/thomashawk">Thomas Hawk</a>
                            </div>
                        </div>
                    </c:when>
                    <%-- Invalid contrast: present solutions --%>
                    <c:otherwise>
                        <div class="row">
                            <div class="result col-lg-12">
                                <h2> <fmt:message key="form.contrastOld"/></h2>
                                <div class="row">
                                    <table id="initial-contrast" class="table">
                                        <caption class="sr-only"><fmt:message key="form.initialContrastCaption"/></caption>
                                        <tr>
                                            <th scope="col" class="col01"><fmt:message key="form.contrastSolutionForeground"/></th>
                                            <th scope="col" class="col02"><fmt:message key="form.contrastSolutionBackground"/></th>
                                            <th scope="col" class="col03"><fmt:message key="form.contrastSolutionRatio"/></th>
                                            <th scope="col" class="col04"><fmt:message key="form.contrastSolutionSample"/></th>
                                        </tr>
                                        <tr>
                                            <td class="col01">
                                                <div class="cercle" style="background-color:${colorModel.foreground}"></div>
                                                <ul class="color-codes">
                                                    <li class="color-value-hsl">${foregroundHSLColor}</li>
                                                    <li class="color-value-rgb">${foregroundColor}</li>
                                                    <li class="color-value-hexa">${colorModel.foreground}</li>
                                                </ul>
                                            </td>
                                            <td class="col02">
                                                <div class="cercle" style="background-color:${colorModel.background}"></div>
                                                <ul class="color-codes">
                                                    <li class="color-value-hsl">${backgroundHSLColor}</li>
                                                    <li class="color-value-rgb">${backgroundColor}</li>
                                                    <li class="color-value-hexa">${colorModel.background}</li>
                                                </ul>
                                            </td>
                                            <td class="col03">
                                                ${oldContrast}
                                            </td>
                                            <td  class="text-sample" style="color:${colorModel.foreground};background-color:${colorModel.background}">
                                                <p style="font-size:20px;">
                                                    <fmt:message key="form.sampleTitle"/><span style="font-weight:bold;"><fmt:message key="form.sampleTitleBold"/></span>
                                                </p>
                                                <p>
                                                    <fmt:message key="form.sampleText"/><span style="font-weight:bold;"><fmt:message key="form.sampleTextBold"/></span>
                                                    <fmt:message key="form.sampleText2"/>
                                                    <span lang="la">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis tincidunt vel lacus id convallis.</span>
                                                </p>
                                            </td>
                                        </tr>
                                    </table>
                                </div><!-- /row -->
                            </div><!-- /col-lg-12 -->
                            <div class ="col-lg-12">
                                <h2> <fmt:message key="form.contrastNew"/> : <fmt:message key="form.resultNumber"><fmt:param value="${resultNumber}"/></fmt:message></h2>
                                    <div class="row">
                                        <table id="contrast-solution" class="table tablesorter">
                                            <caption id="theCaption" class="sr-only"><fmt:message key="form.contrastSolutionCaption"/></caption>
                                        <thead>
                                            <tr>
                                                <th scope="col" class="col01"><fmt:message key="form.contrastSolutionForeground"/></th>
                                                <th scope="col" class="col02"><fmt:message key="form.contrastSolutionBackground"/></th>
                                                <th scope="col" class="col03"><fmt:message key="form.contrastSolutionRatio"/></th>
                                                <th scope="col" class="col04"><fmt:message key="form.contrastSolutionSample"/></th>
                                            </tr>
                                        </thead>
                                        <c:if test="${colorModel.isBackgroundTested}">
                                            <c:forEach var="result" items="${colorResult.suggestedColors}">
                                                <tr>
                                                    <td class="col01">
                                                        <div class="cercle" style="background-color:rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue})"></div>
                                                        <ul class="color-codes">
                                                            <li class="color-value-hsl">${result.hslColorComp}</li>
                                                            <li class="color-value-rgb">rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue})</li>
                                                            <li class="color-value-hexa">${result.hexaColorComp}</li>
                                                        </ul>
                                                    </td>
                                                    <td class="col02">
                                                        <div class="cercle" style="background-color:rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})"></div>
                                                        <ul class="color-codes">
                                                            <li class="color-value-hsl">${result.hslColor}</li>
                                                            <li class="color-value-rgb">rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})</li>
                                                            <li class="color-value-hexa">${result.hexaColor}</li>
                                                        </ul>
                                                    </td>
                                                    <td class="col03">
                                                        ${result.contrast}
                                                    </td>
                                                    <td  class="text-sample" style="
                                                         color:rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue});
                                                         background-color:rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})">

                                                        <p style="font-size:20px;">
                                                            <fmt:message key="form.sampleTitle"/><span style="font-weight:bold;"><fmt:message key="form.sampleTitleBold"/></span>
                                                        </p>
                                                        <p>
                                                            <fmt:message key="form.sampleText"/><span style="font-weight:bold;"><fmt:message key="form.sampleTextBold"/></span>
                                                            <fmt:message key="form.sampleText2"/>
                                                            <span lang="la">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis tincidunt vel lacus id convallis.</span>
                                                        </p> 
                                                    </td>
                                                </tr>
                                            </c:forEach>    
                                        </c:if>
                                        <c:if test="${!colorModel.isBackgroundTested}">
                                            <c:forEach var="result" items="${colorResult.suggestedColors}"> 
                                                <tr>
                                                    <td class="col01">
                                                        <div class="cercle" style="background-color:rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})"></div>
                                                        <ul class="color-codes">
                                                            <li class="color-value-hsl">${result.hslColor}</li>
                                                            <li class="color-value-rgb">rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})</li>
                                                            <li class="color-value-hexa">${result.hexaColor}</li>
                                                        </ul>
                                                    </td>
                                                    <td class="col02">
                                                        <div class="cercle" style="background-color:rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue})"></div>
                                                        <ul class="color-codes">
                                                            <li class="color-value-hsl">${result.hslColorComp}</li>
                                                            <li class="color-value-rgb">rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue})</li>
                                                            <li class="color-value-hexa">${result.hexaColorComp}</li>
                                                        </ul>
                                                    </td>
                                                    <td class="col03">
                                                        ${result.contrast}
                                                    </td>
                                                    <td  class="text-sample" style="
                                                         color:rgb(${result.color.red}, ${result.color.green}, ${result.color.blue});
                                                         background-color:rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue})">
                                                        <p style="font-size:20px;">
                                                            <fmt:message key="form.sampleTitle"/><span style="font-weight:bold;"><fmt:message key="form.sampleTitleBold"/></span>
                                                        </p>
                                                        <p>
                                                            <fmt:message key="form.sampleText"/><span style="font-weight:bold;"><fmt:message key="form.sampleTextBold"/></span>
                                                            <fmt:message key="form.sampleText2"/>
                                                            <span lang="la">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis tincidunt vel lacus id convallis.</span>
                                                        </p> 
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </table>
                                </div><!-- /row -->
                            </div><!-- /col-lg-12 -->
                        </div><!-- /row -->
                    </c:otherwise>
                </c:choose>        
            </c:if>

            
        </div>  <!-- class="container' -->
        <%@include file='/WEB-INF/template/footer.jspf' %>
        <!-- From  -->
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/jquery-ui.min.js"></script>
        <script src="Js/jquery.tablesorter.min.js"></script>
        <script src="Js/accessible-min.js"></script>
    </body>

</html>
</compress:html>
