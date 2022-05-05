import { User } from '@main/api/models'

export const appKeyOf = ( path: String ) => path.replace( "app/", "" )

export interface AppState {
    authorizedUser?: User
}
