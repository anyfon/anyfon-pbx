declare module '*.vue' {
    import { defineComponent } from 'vue'
    const Component: ReturnType<typeof defineComponent>;
    export default Component;
}

declare module 'quasar/src/install-quasar' {
    import { App } from 'vue'

    function installQuasar( app: App, options: any ): void

    export default installQuasar
}
