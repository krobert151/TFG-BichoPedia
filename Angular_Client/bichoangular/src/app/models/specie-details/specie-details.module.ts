export interface SpecieDetailsResponse {
  ScientificName: string;
  danger:         string;
  mainPhoto:      string;
  info:           Article[];
  identification: Article[];
  cares:          Article[];
}

export interface Article {
  title:       string;
  description: string;
  archives:    string[];
}

// Converts JSON strings to/from your types
export class Convert {
  public static toSpecieDetailsResponse(json: string): SpecieDetailsResponse {
      return JSON.parse(json);
  }

  public static specieDetailsResponseToJson(value: SpecieDetailsResponse): string {
      return JSON.stringify(value);
  }
}
