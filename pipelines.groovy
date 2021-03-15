namespaces = ['services', 'services/order']
pipelineDetails = [
        [
                name                : "order-service",
                namespace           : "services/order",
                repo                : "https://github.com/anuj13mathur/order-service.git",
                branch              : "main",
                pipelineRelativePath: "Jenkinsfile"
        ],
        [
                name                : "shipping-service",
                namespace           : "services",
                repo                : "https://github.com/anuj13mathur/shipping-service.git",
                branch              : "main",
                pipelineRelativePath: "Jenkinsfile"
        ],
        [
                name                : "order-worker",
                namespace           : "services/order",
                repo                : "https://github.com/anuj13mathur/order-worker.git",
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
