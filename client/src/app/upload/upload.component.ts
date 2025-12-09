import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  imports: [CommonModule],
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {
  uploadedFiles: File[] = [];
  errorMessage: string = '';

  onFileSelected(event: any) {
    const files: FileList = event.target.files;
    this.errorMessage = '';

    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      if (file.type === 'application/pdf')
        this.uploadedFiles.push(file);
      else
        this.errorMessage = 'Only PDF files are allowed!';

    }

    event.target.value = '';
  }

  get files() {
    return this.uploadedFiles.length;
  }

  removeFile(index: number) {
    this.uploadedFiles.splice(index, 1);
  }

}
