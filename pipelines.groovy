namespaces = ['services']
pipelineDetails = [
        [
                name                : "order-service",
                namespace           : "services",
                repo                : "https://github.com/anuj13mathur/order-service.git",
                branch              : "main",
                pipelineRelativePath: "Jenkinsfile"
        ],
        [
                name                : "customer-service",
                namespace           : "services",
                repo                : "https://github.com/anuj13mathur/customer-service.git",
                branch              : "main",
                pipelineRelativePath: "Jenkinsfile"
        ],
        [
                name                : "billing-service",
                namespace           : "services",
                repo                : "https://github.com/anuj13mathur/billing-service.git",
                branch              : "main",
                pipelineRelativePath: "Jenkinsfile"
        ]
]
namespaces.each {
    folder("${it}")
}
pipelineDetails.each {
    pipelineDetail = it
    pipelineJob("${pipelineDetail['namespace']}" + "/" + "${pipelineDetail['name']}") {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url("${pipelineDetail['repo']}")
                        }
                        branch("*/${pipelineDetail['branch']}")
                        extensions {
                            wipeOutWorkspace()
                        }
                    }

                }
                scriptPath("${pipelineDetail['pipelineRelativePath']}")
            }
        }
    }
}
