export class SummaryDto {
    id: number;
    userId: string;
    url: string;
    title: string;
    text: string;
    date: string;

    constructor(id: number, userId: string, url: string, title: string, text: string, date: string) {
        this.id = id;
        this.userId = userId;
        this.url = url;
        this.title = title;
        this.text = text;
        this.date = date;
     }
}