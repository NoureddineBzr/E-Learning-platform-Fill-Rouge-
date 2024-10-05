import { Component, OnInit, ViewChild } from '@angular/core';
import { FormMode, adminUrls, dialog_h_md, dialog_w_md, imagesUrls } from '../../../../constants/constants';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';

import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { CourseDialogFormComponent } from '../course-dialog-form/course-dialog-form.component';
import { TranslateService } from '@ngx-translate/core';
import { ComponentUtilsService } from 'src/app/utils/components.utl.service';
import { Course } from 'src/app/model/course.model';
import { CourseService } from 'src/app/service/courses.service';
import { take } from 'rxjs';
import { AppResponse } from 'src/app/model/app_response.model';
import Swal from 'sweetalert2';
import { LectureDialogFormComponent } from '../lecture-dialog-form/lecture-dialog-form.component';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {
  ADD_COURSE_URL: string = adminUrls.addCourse;
  IMAGES_URL= imagesUrls;
  coursesList: Course[];

  constructor(public dialog: MatDialog,
    private translate: TranslateService,
    private componentUtilsService: ComponentUtilsService,
    private courseService: CourseService

  ) { }

  displayedColumns: string[] = ['title', 'description', 'price', 'isFree', 'rating', 'enrolledStudents', 'coverImage', 'actions'];
  dataSource = new MatTableDataSource<Course>(null);

  @ViewChild(MatPaginator) paginator: MatPaginator;

 

  ngOnInit(): void {
    this.getAllCources();
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
  getAllCources() {
    this.courseService.getAll().pipe(take(1)).subscribe({
      next: (response: AppResponse) => {
        this.coursesList = response.data;
        this.dataSource = new MatTableDataSource<Course>(this.coursesList);
      },
      error: (error: Error) => {
        Swal.fire({ 
          icon: "error",
          title: error.message,
          showConfirmButton: true,
         
        });
      }
    }
    );
  }


  openCreateDialog() { 

    const data = {
      title: this.translate.instant('add_new_course'),
      formMode: FormMode.CREATE
    };
    const dialogRef = this.dialog.open(CourseDialogFormComponent, {
      width: dialog_w_md,
      height: 'auto',
      data: data
    }); 

    dialogRef.afterClosed().pipe(take(1)).subscribe(result => {
      if(result){
        this.coursesList.push(result);
        this.dataSource = new MatTableDataSource<Course>(this.coursesList);
      }
    });
  }

  openEditDialog(courseData: any) {
    const data = {
      title: this.translate.instant('edit_course'),
      formMode: FormMode.EDIT,
      courseData: courseData
    };
    const dialogRef = this.dialog.open(CourseDialogFormComponent, {
      width: dialog_w_md,
      height: 'auto',
      data: data
    });
 
    dialogRef.afterClosed().pipe(take(1)).subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }


  openAddNewLectureDialog(courseId: any) {
    const data = {
      title: this.translate.instant('add_lecture'),
      formMode: FormMode.CREATE,
      courseId: courseId
    };
    const dialogRef = this.dialog.open(LectureDialogFormComponent, {
      width: dialog_w_md,
      height: 'auto',
      data: data
    });
 
    dialogRef.afterClosed().pipe(take(1)).subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
