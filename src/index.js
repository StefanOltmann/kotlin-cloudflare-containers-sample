import {Container, getContainer} from "@cloudflare/containers";

export class SampleContainer extends Container {
    defaultPort = 8080;
    sleepAfter = "30s";
}

export default {

    async fetch(request, env) {

        const containerInstance = getContainer(env.CONTAINER);

        return containerInstance.fetch(request);
    }
};