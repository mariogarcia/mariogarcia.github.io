# tag::processing_variables[]
import logging
import ntpath
import os

from addict import Dict
from yaml import add_constructor, load, Loader, YAMLError


log = logging.getLogger("config")


def process_env_directive(loader, node):
    log.info("procesing !env: {}".format(node.value))

    node_val = node.value
    splitted = node_val.split(":")
    
    if len(splitted) == 2: # <1>
        key, value = splitted

        return os.environ.get(key) or value # <2>
    else:
        return os.environ.get(node_val) # <3>
        

add_constructor(u'!env', process_env_directive)
# end::processing_variables[]

class YamlConfigLoader:
    def load(self, env, path):
        path = self.resolve_name_with_environment(env, path)

        if path and os.path.exists(path): 
            with open(path, 'r') as ymlfile:
                try:                
                    yaml = load(ymlfile, Loader=Loader) 
                    conf = Dict(yaml) 

                    return conf
                except YAMLError as error:
                    log.error("config/error/yaml: {}".format(error))
        else:
            log.error("config/error/not_found: {}".format(path))

    def resolve_name_with_environment(self, env, path):
        parent, filename = ntpath.split(path)
        name, ext = filename.split(".")

        if env:
            return os.path.join(parent,"{}-{}.{}".format(name, env, ext))
        else:
            return os.path.join(parent, filename)