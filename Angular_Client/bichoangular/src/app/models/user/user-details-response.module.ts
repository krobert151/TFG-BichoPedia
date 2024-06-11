export interface UserDetailsResponse {
  id: string
  username: string
  email: string
  profilePhoto: string
  roles: string[]
  encounters: UserDetailsResponseEncounter[]
  articles: UserDetailsResponseArticle[]
  savedLists: UserDetailsResponseSavedList[]
  level: number
  exp: number
  percentExp: number
  accountNonExpired: boolean
  accountNonLocked: boolean
  credentialsNonExpired: boolean
  enabled: boolean
  createdAt: string
  old: string
  passwordExpiredAt: string
}

export interface UserDetailsResponseEncounter {
  id: string
  name: string
}

export interface UserDetailsResponseArticle {
  id: string
  name: string
}

export interface UserDetailsResponseSavedList {
  id: string
  name: string
}
