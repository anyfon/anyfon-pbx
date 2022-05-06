import installQuasar from 'quasar/src/install-quasar'
import { App } from 'vue'

import {
    QAvatar,
    QBtn,
    QCheckbox,
    QIcon,
    QInput,
    QItem,
    QItemLabel,
    QItemSection,
    QField,
    QList,
    QResizeObserver,
    QRouteTab,
    QSplitter,
    QSeparator,
    QSelect,

    QTab,
    QTabs,
    QTable,
    QToggle,


    LocalStorage,
    Ripple
} from 'quasar'

const components = {
    QAvatar,
    QBtn,
    QCheckbox,
    QIcon,
    QInput,
    QItem,
    QItemLabel,
    QItemSection,
    QField,
    QList,
    QResizeObserver,
    QRouteTab,

    QSplitter,
    QSeparator,
    QSelect,
    QTab,
    QTabs,
    QTable,
    QToggle,
}

const plugins = {
    LocalStorage
}

const directives = {
    Ripple
}

export default ( app: App ) => {
    installQuasar( app, { components, directives, plugins } )
}
