export interface User {
    id: String
    firstName: String
    middleName?: String
    lastName: String
    role: UserRole
    email: String
    phoneNumber: String
    enabled: Boolean
}


export enum UserRole {
    ROOT = 'ROOT',
    MANAGER = 'MANAGER',
    CUSTOMER = 'CUSTOMER'
}
