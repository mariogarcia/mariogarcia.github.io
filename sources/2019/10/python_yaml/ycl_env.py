import logging
import ntpath
import os

from addict import Dict
from yaml import load, Loader, YAMLError


log = logging.getLogger("config")


class YamlConfigLoader:
    def load(self, env, path):
        path = self.resolve_name_with_environment(env, path)

        if path and os.path.exists(path): 
            with open(path, 'r') as ymlfile:
                try:
                    yaml = load(ymlfile, Loader=Loader) # <1>
                    conf = Dict(yaml) # <2>

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