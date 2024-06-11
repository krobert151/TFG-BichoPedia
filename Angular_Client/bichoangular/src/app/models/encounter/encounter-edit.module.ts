export interface EncounterEdit {
  encounterId: string;
  specieId:    string;
  description: string;
  date:        Date|undefined;
  location:    string;
  photos:      string[];
}