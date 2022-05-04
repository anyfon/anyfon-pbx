declare module '*.vue' {
    import Vue from 'vue';
    export default Vue
}

declare module 'quasar/src/install-quasar' {
    import { App } from 'vue'

    function installQuasar( app: App, options: any ): void

    export default installQuasar
}
