declare module '*.vue'

declare module 'quasar/src/install-quasar' {
    import { App } from 'vue'

    function installQuasar( app: App, options: any ): void


    export default installQuasar
}
