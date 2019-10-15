import os
from ycl_env_var import YamlConfigLoader

# tag::env_variables[]
def test_simple():
    # given: a yaml loader
    loader = YamlConfigLoader()

    # when: setting a environment variable 
    os.environ["USERNAME"] = "outsider"
    
    # and: loading a pro configuration file (config-env.yml)
    yaml_pro = loader.load("env", "config.yml")

    # then: we should get a value from environment variable
    assert yaml_pro.database.user == "outsider"

    # and: because we didn't set the logger variable we get it
    # from the default value
    assert yaml_pro.database.host == "localhost"

# end::env_variables[]