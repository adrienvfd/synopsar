export class SummaryDtoLite {
  id?: number | null = null;
  url?: string = "";
  text?: string = "";

  constructor(id?: number | null, url?: string, text?: string) {
    this.id = id;
    this.url = url;
    this.text = text;
  }
}