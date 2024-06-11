export interface GetUserPermissionsDTO {
  username: string
  email: string
  accountNonExpired: boolean
  accountNonLocked: boolean
  credentialsNonExpired: boolean
  enabled: boolean
}
