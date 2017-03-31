<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <body id="contrast-finder-page">
            <%@include file='/WEB-INF/template/header.jspf' %>
            <main class="container" role="main">
                <span id="forkongithub" lang="en"><a href="https://github.com/Tanaguru/Contrast-Finder">Fork me on GitHub</a></span>
                
                <div id="set-up-form" class="row">
                    <div class="col-lg-12">
                        <h2 class="main-title"><fmt:message key="form.description"/></h2>
                        <c:set var="actionUrl">
                            <c:url value="result.html"></c:url>
                        </c:set>
                        <form:form name="formulaire" commandName="colorModel" method="GET" role="form" action="${actionUrl}">
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
                                <fieldset>
                                    <legend><fmt:message key="form.foregroundColor"/> <span id="foreground-sample" class="color-sample sample-bordered">
                                        <span id="foreground-sample-invalid" class="invalid-color"><fmt:message key="form.invalidColor"/></span>
                                    </span></legend>
                                    <span class="help-block"><fmt:message key="form.rgbHelp"/></span>
                                    <label for="foreground-red">
                                        <fmt:message key="form.red"/><input id="foreground-red" class="form-control" type="number" min="0" max="255" aria-describedby="foreground-sample-invalid">
                                    </label>
                                    <label for="foreground-green">
                                        <fmt:message key="form.green"/><input id="foreground-green" class="form-control" type="number" min="0" max="255" aria-describedby="foreground-sample-invalid">
                                    </label>
                                    <label for="foreground-blue">
                                        <fmt:message key="form.blue"/><input id="foreground-blue" class="form-control" type="number" min="0" max="255" aria-describedby="foreground-sample-invalid">
                                    </label>
                                    <span id="help-block-foreground" class="help-block"><fmt:message key="form.help"/></span> 
                                    <label for="foreground-input">
                                        <fmt:message key="form.hexadecimal"/><form:input id="foreground-input" path="foreground" type="text" class="form-control" aria-describedby="help-block-foreground foreground-sample-invalid"/>
                                    </label>
                                </fieldset>
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
                                <fieldset>
                                    <legend><fmt:message key="form.backgroundColor"/> <span id="background-sample" class="color-sample sample-bordered">
                                        <span id="background-sample-invalid" class="invalid-color"><fmt:message key="form.invalidColor"/></span>
                                    </span></legend>
                                    <span class="help-block"><fmt:message key="form.rgbHelp"/></span>
                                    <label for="background-red">
                                        <fmt:message key="form.red"/><input id="background-red" class="form-control" type="number" min="0" max="255" aria-describedby="background-sample-invalid">
                                    </label>
                                    <label for="background-green">
                                        <fmt:message key="form.green"/><input id="background-green" class="form-control" type="number" min="0" max="255" aria-describedby="background-sample-invalid">
                                    </label>
                                    <label for="background-blue">
                                        <fmt:message key="form.blue"/><input id="background-blue" class="form-control" type="number" min="0" max="255" aria-describedby="background-sample-invalid">
                                    </label>
                                    <span id="help-block-background" class="help-block"><fmt:message key="form.help"/></span>
                                    <label for="background-input" class="control-label">
                                        <fmt:message key="form.hexadecimal"/><form:input id="background-input" path="background" type="text" class="form-control" aria-describedby="help-block-background background-sample-invalid"/>
                                    </label>
                                </fieldset>
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
                                <label for="ratio" class="control-label"><fmt:message key="form.ratio"/></label>
                                <form:select class="form-control" path="ratio">
                                    <form:option value="3"></form:option>
                                    <form:option value="4.5"></form:option>
                                    <form:option value="7"></form:option>
                                </form:select>
                                <form:errors path="ratio" cssClass="help-block"/>
                                <div>
                                    <p class="help-block">
                                        <fmt:message key="form.sc-intro"/> <abbr title="Web Content Accessibility Guidelines" lang="en">WCAG</abbr>, <a href="<fmt:message key="form.wcagLink"/>"><fmt:message key="form.sc"/></a> <fmt:message key="form.sc-info"/>
                                    </p>
                                     <p class="help-block">
                                        <fmt:message key="form.sc-intro-fr"/> <abbr title="Référentiel Général d'Accessibilité des Administrations" lang="fr">RGAA</abbr> 3.0 2016, <fmt:message key="form.sc-intro-fr-2"/> <a href="<fmt:message key="form.rgaaLink"/>"><fmt:message key="form.sc-fr"/></a>.
                                    </p>
                                </div>
                            </div>
                            <div class="form-group modify">
                                <fieldset>
                                    <legend><fmt:message key="form.component"/></legend>
                                    <div class="fieldset-fields">
                                        <label for="isBackgroundTested1">
                                            <form:radiobutton name="" path="isBackgroundTested" value="false"/>
                                            <fmt:message key="form.componentForeground"/>
                                        </label>
                                        <label for="isBackgroundTested2">
                                            <form:radiobutton name="" path="isBackgroundTested" value="true"/>
                                            <fmt:message key="form.componentBackground"/>
                                        </label>   
                                    </div>
                                </fieldset>
                            </div>
                            <c:set var="algoOnError">
                                <form:errors path="algo"/>
                            </c:set>
                            <c:choose>
                                <c:when test="${not empty algoOnError}">
                                    <c:set var="algoOnError" value="has-error"/>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group suggest ${algoOnError}">
                                <fieldset>
                                    <legend><fmt:message key="form.objectifs"/></legend>
                                    <div class="fieldset-fields">
                                         <label for="algo1">
                                            <form:radiobutton path="algo" value="Rgb"/>
                                            <fmt:message key="form.algoRGB"/>
                                        </label>  
                                        <label for="algo2">
                                            <form:radiobutton path="algo" value="HSV"/>
                                            <fmt:message key="form.algoHSV"/>
                                        </label>
                                        <c:if test="${algoOnError == 'has-error'}">
                                            <span class="help-block"><fmt:message key="form.invalidAlgo"/></span>
                                        </c:if>
                                    </div>
                                </fieldset>
                            </div>
                            <div class="form-group">
                                <fmt:message key="form.validate" var="validateButton"/>
                                <input id="submit-button" type="submit" class="btn btn-default" value="${validateButton}"/>
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
                                <div class="col-lg-12" id="image-reference">
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
                                </div>
                                <div id="old-contrast" class="result col-lg-12">
                                    <table id="initial-contrast" class="table">
                                        <caption class="sr-only"><fmt:message key="form.initialContrastCaption"/></caption>
                                        <tr>
                                            <th scope="col" class="col01"><fmt:message key="form.contrastSolutionForeground"/></th>
                                            <th scope="col" class="col02"><fmt:message key="form.contrastSolutionBackground"/></th>
                                            <th scope="col" class="col04"><fmt:message key="form.contrastSolutionSample"/></th>
                                            <th scope="col" class="col03"><fmt:message key="form.contrastSolutionRatio"/></th>
                                            <th scope="col" class="col05"><fmt:message key="form.contrastSolutionDistance"/></th>
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
                                            <td  class="text-sample" style="color:${colorModel.foreground};background-color:${colorModel.background}">
                                                <p style="font-size:20px;">
                                                    <fmt:message key="form.sampleTitle"/><span style="font-weight:bold;"><fmt:message key="form.sampleTitleBold"/></span>
                                                </p>
                                                <p>
                                                    <fmt:message key="form.sampleText"/><span style="font-weight:bold;"><fmt:message key="form.sampleTextBold"/></span>
                                                    <fmt:message key="form.sampleText2"/>
                                                </p>
                                            </td>
                                            <td class="col03">
                                                ${oldContrast}
                                            </td>
                                            <td class="col05">
                                                ${oldDistance}
                                            </td>
                                        </tr>
                                    </table>
                                </div><!-- /col-lg-12 -->
                                <div class ="col-lg-12">
                                    <c:choose>
                                        <c:when test="${colorResult.numberOfSuggestedColors == 0}">
                                            <div class="h2-on-empty-result">
                                            </c:when>
                                            <c:otherwise>
                                                <div>
                                                </c:otherwise>
                                            </c:choose>
                                            <h2> <fmt:message key="form.contrastNew"/> : <fmt:message key="form.resultNumber"><fmt:param value="${resultNumber}"/></fmt:message>
                                                <span id="tested-color">(<fmt:formatNumber value="${colorResult.numberOfTestedColors}"/> <fmt:message key="form.testedColors"/>)</span></h2>
                                        </div>
                                        <c:if test="${colorResult.numberOfSuggestedColors > 0}">
                                            <table id="contrast-solution" class="table tablesorter">
                                                <caption id="theCaption" class="sr-only"><fmt:message key="form.contrastSolutionCaption"/></caption>
                                                <thead>
                                                    <tr>
                                                        <th scope="col" class="col01"><fmt:message key="form.contrastSolutionForeground"/></th>
                                                        <th scope="col" class="col02"><fmt:message key="form.contrastSolutionBackground"/></th>
                                                        <th scope="col" class="col04"><fmt:message key="form.contrastSolutionSample"/></th>
                                                        <th scope="col" class="col03"><fmt:message key="form.contrastSolutionRatio"/></th>
                                                        <th scope="col" class="col05"><abbr title="<fmt:message key="form.contrastSolutionDistanceEx"/>">
                                                                <fmt:message key="form.contrastSolutionDistance"/></abbr></th>
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
                                                            <td  class="text-sample" style="
                                                                 color:rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue});
                                                                 background-color:rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})">

                                                                <p style="font-size:20px;">
                                                                    <fmt:message key="form.sampleTitle"/><span style="font-weight:bold;"><fmt:message key="form.sampleTitleBold"/></span>
                                                                </p>
                                                                <p>
                                                                    <fmt:message key="form.sampleText"/><span style="font-weight:bold;"><fmt:message key="form.sampleTextBold"/></span>
                                                                    <fmt:message key="form.sampleText2"/>
                                                                </p> 
                                                            </td>
                                                            <td class="col03">
                                                                ${result.contrast}
                                                            </td>
                                                            <td class="col05">
                                                                ${result.distance}
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
                                                            <td  class="text-sample" style="
                                                                 color:rgb(${result.color.red}, ${result.color.green}, ${result.color.blue});
                                                                 background-color:rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue})">
                                                                <p style="font-size:20px;">
                                                                    <fmt:message key="form.sampleTitle"/><span style="font-weight:bold;"><fmt:message key="form.sampleTitleBold"/></span>
                                                                </p>
                                                                <p>
                                                                    <fmt:message key="form.sampleText"/><span style="font-weight:bold;"><fmt:message key="form.sampleTextBold"/></span>
                                                                    <fmt:message key="form.sampleText2"/>
                                                                </p> 
                                                            </td>
                                                            <td class="col03">
                                                                ${result.contrast}
                                                            </td>
                                                            <td class="col05">
                                                                ${result.distance}
                                                            </td>      
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </table>
                                        </c:if>
                                        <div id="notSatisfied">
                                            <fmt:message key="form.notSatisfied" />
                                        </div>
                                        <c:if test="${colorResult.numberOfSuggestedColors > 0}">
                                            <c:set var="retryUrl">
                                                <c:url value="result.html?foreground=${colorModel.foreground}&amp;background=${colorModel.background}&amp;algo=${otherAlgo}&amp;ratio=${colorModel.ratio}&amp;isBackgroundTested=${colorModel.isBackgroundTested}"></c:url>
                                            </c:set>
                                            <div class="noResult">
                                                <c:if test="${algo == 'HSV'}">
                                                    <a href="${fn:replace(retryUrl, '#', '%23')}"><fmt:message key="form.tryRgb"/></a>
                                                </c:if>
                                                <c:if test="${algo == 'Rgb'}">
                                                    <a href="${fn:replace(retryUrl, '#', '%23')}"><fmt:message key="form.tryHsv"/></a>
                                                </c:if>
                                            </div>
                                        </c:if>
                                        <c:if test="${colorResult.numberOfSuggestedColors == 0}">
                                            <c:set var="retryUrl">
                                                <c:url value="result.html?foreground=${colorModel.foreground}&amp;background=${colorModel.background}&amp;algo=${algo}&amp;ratio=${colorModel.ratio}&amp;isBackgroundTested=${!colorModel.isBackgroundTested}"></c:url>
                                            </c:set>
                                            <div class="noResult">
                                                <fmt:message key="form.anyResult"/><a href="${fn:replace(retryUrl, '#', '%23')}"><fmt:message key="form.changeComponent"/>
                                                    <c:if test="${colorModel.isBackgroundTested}"><strong><fmt:message key="form.oppositeComponentBackground"/></strong></a>.</c:if>
                                                <c:if test="${!colorModel.isBackgroundTested}"><strong><fmt:message key="form.oppositeComponentForeground"/></strong></a>.</c:if>
                                                </div>
                                        </c:if>
                                    </div><!-- /col-lg-12 -->
                                </div><!-- /row -->
                            </c:otherwise>
                        </c:choose>        
                    </c:if>
                </main>

                <!-- Footer -->
                <%@include file='/WEB-INF/template/footer.jspf' %>
                <!-- From  -->
                <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
                <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.0/jquery-ui.min.js"></script>
                <c:if test="${colorResult.numberOfSuggestedColors > 0}">
                    <script src="Js/jquery.tablesorter.min.js"></script>
                    <script src="Js/accessible-min.js"></script>
                </c:if>
                <script src="Js/bootstrap.min.js"></script>
                <script src="Js/affix.js"></script>
                <script src="Js/sample.color.js"></script>
                <script src="Js/menu.js"></script>
        </body>

    </html>
</compress:html>
