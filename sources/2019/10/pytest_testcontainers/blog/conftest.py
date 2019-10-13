# tag::imports[]
import logging

import pytest

from sqlalchemy import create_engine
from sqlalchemy.orm import (scoped_session, sessionmaker)

from testcontainers.postgres import PostgresContainer
# end::imports[]

from blog.factories import Factories
from blog.models import Base

# tag::session[]
log = logging.getLogger()


@pytest.fixture(scope="session")
def session(request):
    log.info("[fixture] starting db container")

    postgres = PostgresContainer("postgres:9.5") # <1>
    postgres.start()
    
    log.info("[fixture] connecting to: {}".format(postgres.get_connection_url()))
    
    # create session with db container information
    engine = create_engine(postgres.get_connection_url()) # <2>
    session = scoped_session(sessionmaker(autocommit=False,autoflush=False,bind=engine))

    # create schema in database
    Base.metadata.create_all(engine) # <3>

    def stop_db(): # <4>
        log.info("[fixture] stopping db container")
        postgres.stop()

    request.addfinalizer(stop_db) # <5>

    return session # <6>
# end::session[]

# tag::session_factories[]
@pytest.fixture(scope="session")
def factories(request, session):
    return Factories(session)
# end::session_factories[]  

# tag::functions[]
@pytest.fixture(scope="function",autouse=True)
def cleanup(request, session): # <1>
    log.info("[fixture] truncating all tables")

    # truncating all tables
    for table in reversed(Base.metadata.sorted_tables): # <2>
        session.execute(table.delete())

    def function_ends(): # <3>
        log.info("[fixture] closing db session")
        session.commit()
        session.close()

    request.addfinalizer(function_ends) # <4>
# end::functions[]

