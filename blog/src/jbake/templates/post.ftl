<#include "header.ftl">

	<#include "menu.ftl">

	<div class="page-header">
		<h1><#escape x as x?xml>${content.title}</#escape></h1>
    	<p><em>${content.date?string("dd MMMM yyyy")}</em></p>
	</div>
    <#if content.tags??>
      <ol class="breadcrumb tagsbar">
      <#list content.tags as rawtag>
        <#assign tag = rawtag?trim?replace(" ", "-")>
        <li><a href="/blog/tags/${tag}.html">${tag}</a></li>
      </#list>
      </ol>
    </#if>
	<p>${content.body}</p>

	<hr />

<#include "footer.ftl">