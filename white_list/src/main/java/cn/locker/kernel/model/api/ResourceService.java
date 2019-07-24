package cn.locker.kernel.model.api;

import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.locker.kernel.model.resource.ResourceDefinition;

@RequestMapping({"/api/resourceService"})
public abstract interface ResourceService
{
  @RequestMapping(value={"/reportResources"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public abstract void reportResources(@RequestParam("appCode") String paramString, @RequestBody Map<String, Map<String, ResourceDefinition>> paramMap);

  @RequestMapping(value={"/getUserResourceUrls"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public abstract Set<String> getUserResourceUrls(@RequestParam("accountId") String paramString);

  @RequestMapping(value={"/getResourceByUrl"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public abstract ResourceDefinition getResourceByUrl(@RequestParam("url") String paramString);
}