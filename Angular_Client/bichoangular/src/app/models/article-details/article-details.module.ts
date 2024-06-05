export interface ArticleDetails {
  id: string;
  title: string;
  text: string;
  approved: boolean;
  archives: string[];
  createdBy: string;
  type: string;
}