package se.skltp.services.riv.clinicalprocess.healthcond.description.getcaredocumentation

import se.skltp.loghandler.testnonfunctional.TPHappyPathAbstract

/**
 * Just test Tolvan Tolvansson - no error latency
 */
class TPHappyPath extends TPHappyPathAbstract with CommonParameters {
  setUp(setUpAbstract(serviceName, urn, responseElement, responseItem, baseUrl))
}
