<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
    <head>

        <title>ColorFinder Open-S</title>
        <link rel="stylesheet"  type="text/css" href="<c:url value="/Css/bootstrap.min.css"/>">
        <link rel="stylesheet"  type="text/css" href="<c:url value="/Css/bootstrap-theme.min.css"/>">
        <link rel="stylesheet"  type="text/css" href="<c:url value="/Css/color-finder.css"/>">
    </head>

    <body id="set-up-form">
        <!-- Titre -->
        <div class="container">
            <div class="page-header">
                <c:set var="properQueryString" scope="page" value="${fn:replace(pageContext.request.queryString, '&', '&amp;')}"/>
                <c:choose>
                    <c:when test="${not empty pageContext.request.queryString}">
                        <c:choose>
                            <c:when test="${fn:contains(pageContext.request.queryString, 'lang=en')}">
                                <c:set var="enUrl" scope="request" value="?${properQueryString}"/>
                                <c:set var="frUrl" scope="request" value="?${fn:replace(properQueryString,
                                                                             'lang=en', 'lang=fr')}" />
                            </c:when>
                            <c:when test="${fn:contains(pageContext.request.queryString, 'lang=fr')}">
                                <c:set var="frUrl" scope="request" value="?${properQueryString}"/>
                                <c:set var="enUrl" scope="request" value="?${fn:replace(properQueryString,
                                                                             'lang=fr', 'lang=en')}" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="frUrl" scope="request" value="?${properQueryString}&amp;lang=fr"/>
                                <c:set var="enUrl" scope="request" value="?${properQueryString}&amp;lang=en"/>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <c:set var="frUrl" scope="request" value="?lang=fr"/>
                        <c:set var="enUrl" scope="request" value="?lang=en"/>
                    </c:otherwise>
                </c:choose>
                <div id="lang-switcher">
                    <a href="${enUrl}" title="Switch to english" lang="en">
                        <abbr title="English">EN</abbr>
                    </a>
                    <a href="${frUrl}" title="Passer en français" lang="fr">
                        <abbr title="Français">FR</abbr>
                    </a>
                </div> 
                <h1>Tanaguru Contrast-Finder</h1>
                <p><fmt:message key="form.description"/></p>

            </div><!-- class="page-header' -->
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
                                <form:input path="foreground" type="color" class="form-control" required="true"/>
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
                                <form:input path="background" type="color" class="form-control" required="true"/>
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
            <!--
            <c:if test="${empty colorResult}">
                    <div class="row">
                        <div class="alert-message block-message">
                            <div class="result col-lg-12">
                                <h2>Contraste Valide</h2>
                                <p>Les couleurs que vous avez choisi sont valides</p>
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
            </c:if>
            -->
            <c:if test="${not empty colorResult}">
                <div class="row">
                    <div class="result col-lg-12">
                        <h2><fmt:message key="form.results"/></h2>
                        <h3> <fmt:message key="form.contrastOld"/></h3>
                        <div class="row">
                            <div class="col-lg-2">
                                <div class="cercle" style="background-color:${colorModel.foreground}"></div>
                                <div class="color-value-rgb">${foregroundColor}</div>
                                <div class="color-value-rgb">${colorModel.foreground}</div>
                            </div>
                            <div class="col-lg-2">
                                <div class="cercle" style="background-color:${colorModel.background}"></div>
                                <div class="color-value-rgb">${backgroundColor}</div>
                                <div class="color-value-rgb">${colorModel.background}</div>
                            </div>
                            <span class="col-lg-5" style="color:${colorModel.foreground};background-color:${colorModel.background}">
                                <fmt:message key="form.sampleWrong"/>
                            </span>
                        </div>
                    </div>
                    <div class ="col-lg-12">
                        <h3> <fmt:message key="form.contrastNew"/></h3>
                        <c:if test="${colorModel.isBackgroundTested}">
                            <c:forEach var="result" items="${colorResult}">
                                <div class="row">
                                    <div class="col-lg-2">
                                        <div class="cercle" style="background-color:rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue})"></div>
                                        <div class="color-value-rgb">rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue})</div>
                                        <div class="color-value-rgb">${result.hexaColorComp}</div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="cercle" style="background-color:rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})"></div>
                                        <div class="color-value-rgb">rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})</div>
                                        <div class="color-value-rgb">${result.hexaColor}</div>

                                    </div>
                                    <span class="col-lg-5" style="
                                          color:rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue});
                                          background-color:rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})">
                                        <fmt:message key="form.sampleRight"/> ${result.contrast}
                                    </span>
                                </div>
                            </c:forEach>    
                        </c:if>
                        <c:if test="${!colorModel.isBackgroundTested}">
                            <c:forEach var="result" items="${colorResult}"> 
                                <div class="row">
                                    <div class="col-lg-2">
                                        <div class="cercle" style="background-color:rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})"></div>
                                        <div class="color-value-rgb">rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})</div>
                                        <div class="color-value-rgb">${result.hexaColor}</div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="cercle" style="background-color:rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue})"></div>
                                        <div class="color-value-rgb">rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue})</div>
                                        <div class="color-value-rgb">${result.hexaColorComp}</div>
                                    </div> 
                                    <span class="col-lg-5" style="
                                          background-color:rgb(${result.comparisonColor.red}, ${result.comparisonColor.green}, ${result.comparisonColor.blue});
                                          color:rgb(${result.color.red}, ${result.color.green}, ${result.color.blue})">
                                        <fmt:message key="form.sampleRight"/> ${result.contrast}
                                    </span>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </c:if>
        </div>  <!-- class="container' -->
        <footer>
            <div id="footer-down">
                © 2013 <a href="http://www.Open-S.com/">Open-S</a> - Version 0.1
            </div>
        </footer>
    </body>

</html>