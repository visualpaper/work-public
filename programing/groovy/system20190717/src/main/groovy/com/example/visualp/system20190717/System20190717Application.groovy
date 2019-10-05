package com.example.visualp.system20190717

import com.example.visualp.system20190717.base.SampleDelegate
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class System20190717Application {

	static void main(String[] args) {
		SpringApplication.run(System20190717Application, args)

		initialize()
	}

	static void initialize() {
		Script dslScript = new GroovyShell().parse(new File("src/main/resources/groovy/sample.groovy"))

		dslScript.setMetaClass(
				createEMC(dslScript.class, {
					ExpandoMetaClass emc ->
						emc.sample = {
							Closure cl ->

								cl.delegate = new SampleDelegate()
								cl.resolveStrategy = DELEGATE_FIRST

								cl()
						}
				})
		)
		dslScript.run()
	}

	static ExpandoMetaClass createEMC(Class scriptClass, Closure cl) {
		ExpandoMetaClass emc = new ExpandoMetaClass(scriptClass, false)

		cl(emc)

		emc.initialize()
		return emc
	}
}
